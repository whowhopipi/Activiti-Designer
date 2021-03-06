package org.activiti.designer.kickstart.form.util;

import java.util.HashMap;
import java.util.Map;

import org.activiti.designer.util.style.StyleUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.IGradientType;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;

/**
 * @author Frederik Heremans
 */
public final class FormComponentStyles {

  // Style ID's
  private static final String INPUT_FIELD_RECTANGLE_STYLE_ID = "input-field-rectangle";
  
  // Colors
  private static final IColorConstant DEFAULT_FOREGROUND_COLOR = new ColorConstant(0, 0, 0);
  
  private static Map<String, Style> styleMap = new HashMap<String, Style>();
  
  private FormComponentStyles() {
    // Private constructor to prevent instantiation
  }
  
  /**
   * @return style that should be used for the rectangle of an input-field.
   */
  public static Style getInputFieldStyle(Diagram diagram) {
    Style style = styleMap.get(INPUT_FIELD_RECTANGLE_STYLE_ID);
    if(style == null) {
      IGaService gaService = Graphiti.getGaService();
      style = gaService.createStyle(diagram, INPUT_FIELD_RECTANGLE_STYLE_ID);
      style.setForeground(gaService.manageColor(diagram, DEFAULT_FOREGROUND_COLOR));
      gaService.setRenderingStyle(style, getDefaultInputFieldColor(diagram));
      style.setLineWidth(20);
      
      styleMap.put(INPUT_FIELD_RECTANGLE_STYLE_ID, style);
    }
    return style;
  }
  
  private static AdaptedGradientColoredAreas getDefaultInputFieldColor(Diagram diagram) {
    final AdaptedGradientColoredAreas agca = StylesFactory.eINSTANCE.createAdaptedGradientColoredAreas();
    agca.setDefinedStyleId(INPUT_FIELD_RECTANGLE_STYLE_ID);
    agca.setGradientType(IGradientType.VERTICAL);
    
    final GradientColoredAreas defaultGradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
    defaultGradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
    final EList<GradientColoredArea> gcas = defaultGradientColoredAreas.getGradientColor();
    
    StyleUtil.addGradientColoredArea(gcas, "FFFFFF", 0,
        LocationType.LOCATION_TYPE_ABSOLUTE_START, "FFFFFF", 3,
        LocationType.LOCATION_TYPE_ABSOLUTE_END, diagram);
    StyleUtil.addGradientColoredArea(gcas, "FFFFFF", 3,
        LocationType.LOCATION_TYPE_ABSOLUTE_END, "ABABAB", 0,
        LocationType.LOCATION_TYPE_ABSOLUTE_END, diagram);
    agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, defaultGradientColoredAreas);
    
    return agca;
  }
  
  
}
