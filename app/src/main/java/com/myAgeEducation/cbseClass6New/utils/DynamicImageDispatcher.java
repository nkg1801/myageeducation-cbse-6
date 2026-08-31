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
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.NumericFractionImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.DirectionDistanceImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.MetroMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.NeighborhoodMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.mappingskills.ZooMapImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.PatternSequenceImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.perimeterarea.TileCoveringImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.pictograph.PictographImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.tabularquestions.TableImageGenerator;
import com.myAgeEducation.cbseClass6New.maths.symmetry.SymmetryImageGenerator;

public class DynamicImageDispatcher {
    public static Bitmap dispatch(Context context, String imageCode) {
        if (imageCode == null || imageCode.isEmpty()) return null;

        if (imageCode.startsWith(ImageCodeType.BARCHART)) {
            return BarChartImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.CIRCLE_GRAPH)) {
            return CircleGraphImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.PICTOGRAPH)) {
            return PictographImageGenerator.generate(context, imageCode);
        } else if (imageCode.startsWith(ImageCodeType.ANGLE)) {
            return AngleImageGenerator.generateImage(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.CLOCK)) {
            return ClockImageGenerator.generateClockImage(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.CALENDAR)) {
            return CalendarImageGenerator.generateCalendarImage(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.DECIMAL_GRID)) {
            return DecimalGridImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.DECIMAL_IMAGE)) {
            return DecimalImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.TABLE)) {
            return TableImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.ZOO_MAP)) {
            return ZooMapImageGenerator.generate(context, imageCode);
        } else if (imageCode.startsWith(ImageCodeType.NEIGHBORHOOD_MAP)) {
            return NeighborhoodMapImageGenerator.generate(context, imageCode);
        } else if (imageCode.startsWith(ImageCodeType.METRO_MAP)) {
            return MetroMapImageGenerator.generate(context, imageCode);
        } else if (imageCode.startsWith(ImageCodeType.PATTERN_SEQUENCE)) {
            return PatternSequenceImageGenerator.generate(context, imageCode);
        } else if (imageCode.startsWith(ImageCodeType.EQUIVALENT_FRACTION)) {
            return EquivalentFractionImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.NUMERIC_FRACTION)) {
            return NumericFractionImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith(ImageCodeType.TILE_COVERING)) {
            return TileCoveringImageGenerator.generate(imageCode);
        } else if (imageCode.startsWith("SYMMETRY")) {
            return SymmetryImageGenerator.generate(imageCode);
        }
        
        return null;
    }
}
