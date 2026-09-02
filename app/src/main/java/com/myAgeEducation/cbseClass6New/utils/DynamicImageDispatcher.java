package com.myAgeEducation.cbseClass6New.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.myAgeEducation.cbseClass6New.maths.LineAndAngle.AngleImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.charts.BarChartImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.circlegraph.CircleGraphImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.datetimecalendar.CalendarImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.datetimecalendar.ClockImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalGridImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.divisions.facts.DivisionPictureImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.EquivalentFractionImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionChoiceGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionComparisonImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.NumericFractionImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.DirectionDistanceImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.MetroMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.NeighborhoodMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.ZooMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.PatternSequenceImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.perimeterarea.PerimeterShapeImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.perimeterarea.TileCoveringImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.pictograph.PictographImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.tabularquestions.TableImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.symmetry.SymmetryImageGenerator;

public class DynamicImageDispatcher {
    public static Bitmap dispatch(Context context, String imageCode) {
        if (imageCode == null || imageCode.isEmpty()) return null;

        if (imageCode.startsWith(ImageCodeType.BARCHART)) {
            return BarChartImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.CIRCLE_GRAPH)) {
            return CircleGraphImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.PICTOGRAPH)) {
            return PictographImageGenerator.generate(context, imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.ANGLE)) {
            return AngleImageGenerator.generateImage(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.CLOCK)) {
            return ClockImageGenerator.generateClockImage(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.CALENDAR)) {
            return CalendarImageGenerator.generateCalendarImage(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.DECIMAL_GRID)) {
            return DecimalGridImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.DECIMAL_IMAGE)) {
            return DecimalImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.TABLE)) {
            return TableImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.ZOO_MAP)) {
            return ZooMapImageGenerator.generate(context, imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.NEIGHBORHOOD_MAP)) {
            return NeighborhoodMapImageGenerator.generate(context, imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.METRO_MAP)) {
            return MetroMapImageGenerator.generate(context, imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.PATTERN_SEQUENCE)) {
            return PatternSequenceImageGenerator.generate(context, imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.EQUIVALENT_FRACTION)) {
            return EquivalentFractionImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.FRACTION_COMPARISON)) {
            return FractionComparisonImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.NUMERIC_FRACTION)) {
            return NumericFractionImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.TILE_COVERING)) {
            return TileCoveringImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith(ImageCodeType.PERIMETER_SHAPE)) {
            return PerimeterShapeImageGenerator.generate(imageCode);
        }
        if (imageCode.startsWith("SYMMETRY")) {
            return SymmetryImageGenerator.generate(imageCode);
        }
        if(imageCode.startsWith(ImageCodeType.SHAPE_PART_FRACTION))
        {
            return FractionImageGenerator.generateFractionImage(imageCode);
        }
        if(imageCode.startsWith(ImageCodeType.DIVISION))
        {
            return DivisionPictureImageGenerator.generate(imageCode);
        }
        if(imageCode.startsWith(ImageCodeType.FRACTION_CHOICE)){
            return FractionChoiceGenerator.generateBitmap(imageCode);
        }
        if(imageCode.startsWith(ImageCodeType.DISTANCE_GRID_QUIZ)) {
            return DirectionDistanceImageGenerator.generate(context, imageCode);
        }

        
        return null;
    }
}
