package com.myAgeEducation.cbseClass6New.maths.perimeterarea;

import com.myAgeEducation.cbseClass6New.maths.utils.PersonNameUtil;
import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PerimeterAreaQuestionGenerator {
    private static final Random RANDOM = new Random();

    public static Question generateQuestion() {
        int type = RANDOM.nextInt(13);
        PerimeterAreaQuestionData data;
        switch (type) {
            case 1: data = generatePerimeterSquareQuestion(); break;
            case 2: data = generatePerimeterRectangleQuestion(); break;
            case 3: data = generateAreaComparisonQuestion(); break;
            case 4: data = generatePerimeterComparisonQuestion(); break;
            case 5: data = generateTileCoveringQuestion(); break;
            case 6: data = generateGridAreaComparisonQuestion(); break;
            case 7: data = generateSameAreaComparisonQuestion(); break;
            case 8: data = generateGridMultiShapeQuiz(); break;
            case 9: data = generateAreaLogicComparisonQuestion(); break;
            case 10: data = generateVolumeCubeQuestion(); break;
            case 11: data = generateVolumeCuboidQuestion(); break;
            case 12: data = generateGridDesignAreaQuestion(); break;
            default: data = generateConceptQuestion();
        }
        return convertToQuestion(data);
    }

    private static PerimeterAreaQuestionData generateGridDesignAreaQuestion() {
        int designType = RANDOM.nextInt(4);
        int cols = 7, rows = 6;
        String imageCodePrefix = "TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=";
        StringBuilder data = new StringBuilder();
        double area = 0;
        String color = "#E65100"; // Orange-ish like image 1

        switch (designType) {
            case 0: // Hollow Rectangle (Image 1 style)
            {
                int w = 3 + RANDOM.nextInt(3); // 3-5
                int h = 3 + RANDOM.nextInt(3); // 3-5
                int startC = 2, startR = 2;
                data.append("5,").append(color).append(",");
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        if (i == 0 || i == w - 1 || j == 0 || j == h - 1) {
                            data.append(startC + i).append(",").append(startR + j).append(",");
                            area++;
                        }
                    }
                }
                data.deleteCharAt(data.length() - 1);
                break;
            }
            case 1: // Polygon with half squares (Image 2 style)
            {
                // Simple octagon-ish: rectangle 3x3 with 4 corner half-squares removed
                // Actually easier to just draw a 3x3 rectangle and subtract corners
                // OR add 5 full squares and 4 half squares
                // Center (2,2) 1x1, mid-edges (2,1),(1,2),(3,2),(2,3) 1x1, corners (1,1),(3,1),(1,3),(3,3) 0.5x0.5
                data.append("0,3,3,1,1,").append(color).append("|"); // center
                data.append("0,3,2,1,1,").append(color).append("|"); // top
                data.append("0,3,4,1,1,").append(color).append("|"); // bottom
                data.append("0,2,3,1,1,").append(color).append("|"); // left
                data.append("0,4,3,1,1,").append(color).append("|"); // right
                
                data.append("1,2,2,1,1,").append(color).append("|"); // TL (Type 1: TR half - wait, let's check types)
                // Type 1: TR half (TL, TR, BR)
                // Type 2: TL half (TL, TR, BL)
                // Type 3: BL half (TL, BL, BR)
                // Type 4: BR half (TR, BR, BL)
                // Corner TL: need type 1
                // Corner TR: need type 2
                // Corner BL: need type 4
                // Corner BR: need type 3
                data.append("1,2,2,1,1,").append(color).append("|");
                data.append("2,4,2,1,1,").append(color).append("|");
                data.append("4,2,4,1,1,").append(color).append("|");
                data.append("3,4,4,1,1,").append(color);
                area = 5 + 4 * 0.5;
                break;
            }
            case 2: // Cutout shape (Image 3 style)
            {
                // Rect 5x3 with some parts removed
                int startC = 2, startR = 2;
                data.append("5,").append(color).append(",");
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == 2 && j == 0) continue; // remove top center
                        data.append(startC + i).append(",").append(startR + j).append(",");
                        area++;
                    }
                }
                data.deleteCharAt(data.length() - 1);
                // Add some triangles at edges
                data.append("|1,1,2,1,1,").append(color).append("|2,7,2,1,1,").append(color);
                area += 1.0;
                break;
            }
            default: // Staircase (Image 4 style)
            {
                color = "#FFB300"; // Yellow-ish
                int startC = 2, startR = 1;
                data.append("5,").append(color).append(",");
                for (int i = 0; i < 5; i++) {
                    int height = (i < 4) ? (i + 1) : 2;
                    for (int j = 0; j < height; j++) {
                        data.append(startC + i).append(",").append(rows - j).append(",");
                        area++;
                    }
                }
                data.deleteCharAt(data.length() - 1);
                break;
            }
        }

        String question = "How much area does the design in the picture cover?";
        String ansStr = (area == (int)area) ? String.valueOf((int)area) : String.valueOf(area);
        String answer = ansStr + " sq units";

        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add(((int)area + 5) + " sq units");
        options.add(((int)area - 2) + " sq units");
        options.add(((int)area + 2) + " sq units");
        Collections.shuffle(options);

        PerimeterAreaQuestionData qData = new PerimeterAreaQuestionData(question, answer, options.toArray(new String[0]), PerimeterAreaQuestionType.GRID_DESIGN_AREA);
        qData.setImageData(imageCodePrefix + data.toString());
        return qData;
    }

    private static PerimeterAreaQuestionData generateConceptQuestion() {
        String[][] concepts = {
            {"The distance around the edge of a figure is its _________", "perimeter", "area", "volume", "length"},
            {"_______ is the amount of surface a figure covers", "area", "perimeter", "boundary", "edge"},
            {"Perimeter of a square = 4 x _________", "side", "area", "length", "width"},
            {"Perimeter of a rectangle = 2 x (length + _________)", "width", "side", "area", "height"},
            {"A _________ is a rectangle with all four sides equal.", "square", "triangle", "circle", "oval"},
            {"To find the perimeter of any figure, we _________ the lengths of all its sides.", "add", "subtract", "multiply", "divide"},
            {"A square has _________ equal sides.", "four", "three", "two", "five"},
            {"In a rectangle, the _________ sides are equal.", "opposite", "all", "adjacent", "no"},
            {"Area is measured in _________ units.", "square", "linear", "cubic", "circular"},
            {"Perimeter is measured in _________ units.", "linear", "square", "cubic", "circular"},
            {"The perimeter of a triangle with sides 3cm, 4cm, and 5cm is _________.", "12 cm", "7 cm", "9 cm", "60 cm"},
            {"If the perimeter of a square is 20 cm, the length of each side is _________.", "5 cm", "4 cm", "10 cm", "80 cm"},
            {"A closed figure made of three line segments is called a _________.", "triangle", "square", "rectangle", "circle"},
            {"Perimeter is a _________ dimensional measurement.", "one", "two", "three", "zero"},
            {"Area is a _________ dimensional measurement.", "two", "one", "three", "four"},
            {"The unit 'sq. cm' is used to measure _________.", "area", "perimeter", "length", "weight"},
            {"We need _________ measurements to find the area of a rectangle.", "two", "one", "three", "four"},
            {"If two figures have the same shape and size, they have _________ area.", "equal", "unequal", "double", "half"},
            {"A _________ does not have a perimeter made of line segments.", "circle", "square", "rectangle", "triangle"},
            {"To find the perimeter of a rectangle, we need its length and _________.", "width", "area", "side", "diagonal"},
            {"Area of a square with side 1 cm is _________.", "1 sq. cm", "1 cm", "4 cm", "4 sq. cm"},
            {"The perimeter of a regular pentagon with side 5 cm is _________.", "25 cm", "20 cm", "30 cm", "5 cm"},
            {"Area of a rectangle = length x _________.", "width", "perimeter", "side", "boundary"},
            {"Standard unit of area is _________.", "square units", "linear units", "meters", "litres"},
            {"A square of side 2 cm has an area of _________.", "4 sq. cm", "4 cm", "8 cm", "2 sq. cm"},
            {"Perimeter of a triangle is the _________ of its three sides.", "sum", "product", "difference", "quotient"},
            {"We use a _________ to measure the length of a small line segment.", "ruler", "weighing scale", "clock", "thermometer"},
            {"Area of a figure _________ if we change its position.", "remains same", "increases", "decreases", "doubles"},
            {"A _________ is a simple closed figure.", "polygon", "open curve", "line", "point"},
            {"Which of these has a larger area?", "A classroom", "A pencil box", "A notebook", "A sharpener"},
            {"Perimeter of a square with side 's' is _________.", "4s", "s x s", "2s", "s + 4"},
            {"To fence a park, we need to know its _________.", "perimeter", "area", "volume", "weight"},
            {"To carpet a room, we need to know its _________.", "area", "perimeter", "height", "length"},
            {"Volume of a cube with side 's' is _________.", "s x s x s", "s x s", "4s", "s + s + s"},
            {"Standard unit of volume is _________.", "cubic units", "square units", "linear units", "kilograms"},
            {"A cuboid has _________ dimensions.", "three", "two", "one", "four"},
            {"The amount of space occupied by a solid is called its _________.", "volume", "area", "perimeter", "surface area"}
        };
        int idx = RANDOM.nextInt(concepts.length);
        String[] item = concepts[idx];
        String[] options = Arrays.copyOfRange(item, 1, 5);
        List<String> optList = Arrays.asList(options);
        Collections.shuffle(optList);
        return new PerimeterAreaQuestionData(item[0], item[1], optList.toArray(new String[0]), PerimeterAreaQuestionType.CONCEPT);
    }

    private static PerimeterAreaQuestionData generatePerimeterSquareQuestion() {
        int side = RANDOM.nextInt(15) + 2;
        String q = String.format("The length of one side of a square is %d cm. What is the perimeter of the square?", side);
        int perimeter = 4 * side;
        String ans = perimeter + " cm";
        List<String> options = new ArrayList<>();
        options.add(ans);
        options.add((perimeter + 4) + " cm");
        options.add((side * side) + " cm");
        options.add((perimeter - 4) + " cm");
        Collections.shuffle(options);
        return new PerimeterAreaQuestionData(q, ans, options.toArray(new String[0]), PerimeterAreaQuestionType.PERIMETER_SQUARE);
    }

    private static PerimeterAreaQuestionData generatePerimeterRectangleQuestion() {
        int length = RANDOM.nextInt(15) + 5;
        int width = RANDOM.nextInt(length - 2) + 2;
        String unit = RANDOM.nextBoolean() ? "cm" : "inches";
        String q = String.format("A rectangle has a length of %d %s and a width of %d %s. What is the perimeter of the rectangle?", length, unit, width, unit);
        int perimeter = 2 * (length + width);
        String ans = perimeter + " " + unit;
        List<String> options = new ArrayList<>();
        options.add(ans);
        options.add((length + width) + " " + unit);
        options.add((length * width) + " " + unit);
        options.add((perimeter + 2) + " " + unit);
        Collections.shuffle(options);
        return new PerimeterAreaQuestionData(q, ans, options.toArray(new String[0]), PerimeterAreaQuestionType.PERIMETER_RECTANGLE);
    }

    private static PerimeterAreaQuestionData generateAreaComparisonQuestion() {
        String[][] items = {
                {"a football ground", "10000000"},
                {"a mobile phone", "50"},
                {"a 55 inch LED TV", "5000"},
                {"a study table", "10000"},
                {"a postage stamp", "5"},
                {"a classroom door", "20000"},
                {"a city park", "50000000"},
                {"a math textbook", "400"},
                {"a currency note", "100"},
                {"a cricket stadium", "15000000"},
                {"a handkerchief", "600"},
                {"a blackboard", "15000"},
                {"a credit card", "40"},
                {"a pillow", "1200"},
                {"a mouse pad", "300"},
                {"a coin", "4"},
                {"a laptop keyboard", "250"},
                {"a single bed sheet", "30000"},
                {"a swimming pool", "500000"},
                {"an airport", "200000000"},
                {"a fingernail", "1"},
                {"a wall calendar", "1500"},
                {"a computer monitor", "1000"},
                {"a standard brick", "150"},
                {"a large dining table", "30000"},
                {"a state", "5000000000000"},
                {"a country", "50000000000000"},
                {"a continent", "500000000000000"},
                {"a classroom floor", "600000"},
                {"a kitchen sponge", "60"},
                {"a door mat", "2400"},
                {"a towel", "8000"}
        };

        boolean findMax = RANDOM.nextBoolean();
        List<Integer> allIndices = new ArrayList<>();
        for (int i = 0; i < items.length; i++) allIndices.add(i);
        Collections.shuffle(allIndices);

        String[] options = new String[4];
        int targetIdx = -1;
        long targetArea = findMax ? -1 : Long.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int idx = allIndices.get(i);
            options[i] = items[idx][0];
            long area = Long.parseLong(items[idx][1]);
            
            if (findMax) {
                if (area > targetArea) {
                    targetArea = area;
                    targetIdx = i;
                }
            } else {
                if (area < targetArea) {
                    targetArea = area;
                    targetIdx = i;
                }
            }
        }

        String q = findMax ? 
                "Which one of the following occupies maximum area?" : 
                "Which one of the following occupies minimum area?";
        String ans = options[targetIdx];
        return new PerimeterAreaQuestionData(q, ans, options, PerimeterAreaQuestionType.AREA_COMPARISON);
    }

    private static PerimeterAreaQuestionData generatePerimeterComparisonQuestion() {
        boolean max = RANDOM.nextBoolean();
        String q = max ? "Which one of the following has maximum perimeter?" : "Which one of the following has minimum perimeter?";
        
        // Use rectangles with different perimeters
        int[][] rects = new int[4][2];
        Set<Integer> perimeters = new HashSet<>();
        while(perimeters.size() < 4) {
            int l = RANDOM.nextInt(20) + 5;
            int w = RANDOM.nextInt(20) + 5;
            int p = 2 * (l + w);
            if (!perimeters.contains(p)) {
                rects[perimeters.size()][0] = l;
                rects[perimeters.size()][1] = w;
                perimeters.add(p);
            }
        }
        
        String[] options = new String[4];
        int targetIdx = -1;
        int targetVal = max ? -1 : 10000;
        
        for (int i = 0; i < 4; i++) {
            int l = rects[i][0];
            int w = rects[i][1];
            int p = 2 * (l + w);
            options[i] = String.format("Rectangle (L:%d, W:%d)", l, w);
            if (max) {
                if (p > targetVal) {
                    targetVal = p;
                    targetIdx = i;
                }
            } else {
                if (p < targetVal) {
                    targetVal = p;
                    targetIdx = i;
                }
            }
        }
        
        return new PerimeterAreaQuestionData(q, options[targetIdx], options, PerimeterAreaQuestionType.PERIMETER_COMPARISON);
    }

    private static PerimeterAreaQuestionData generateTileCoveringQuestion() {
        int cols = 8 + RANDOM.nextInt(5); // 8-12
        int rows = 6 + RANDOM.nextInt(3); // 6-8
        int totalArea = cols * rows;

        int redSize = 2 + RANDOM.nextInt(2); // 2x2 or 3x3
        double redArea = 0.5 * redSize * redSize;
        
        // Data format: TYPE,COL,ROW,W,H,COLOR
        String blueData = "0,2,2,1,1,#2196F3";
        String redData = "1,4,2," + redSize + "," + redSize + ",#F44336";
        String greenData = "3,4," + rows + ",1,1,#4CAF50";

        String imageCode = "TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=" + blueData + "|" + redData + "|" + greenData;

        int questionSubtype = RANDOM.nextInt(3);
        String question;
        String answer;
        double tileArea;
        String shapeName;

        switch (questionSubtype) {
            case 0:
                shapeName = "Green triangles";
                tileArea = 0.5;
                break;

            case 1:
                shapeName = "Red triangles";
                tileArea = redArea;
                break;

            default:
                shapeName = "Blue squares";
                tileArea = 1.0;
                break;
        }

        if(RANDOM.nextBoolean()) {
            question = PersonNameUtil.getFemaleName() +
            " is playing with tiles. She covers her desk with different shapes as shown below. " +
                    "Look at the different tiles on her desk and answer how many of the following shapes will cover the desk: " + shapeName;
        }
        else {
            question = PersonNameUtil.getMaleName() + " is playing with tiles. He covers his desk with different shapes as shown below. " +
                    "Look at the different tiles on his desk and answer how many of the following shapes will cover the desk: " + shapeName;
        }

        int numTiles = (int) Math.round(totalArea / tileArea);
        answer = String.valueOf(numTiles);

        String[] options = new String[4];
        options[0] = answer;
        options[1] = String.valueOf(numTiles + 5);
        options[2] = String.valueOf(numTiles / 2);
        options[3] = String.valueOf(numTiles + 10);
        
        List<String> optionList = new ArrayList<>(Arrays.asList(options));
        Collections.shuffle(optionList);
        options = optionList.toArray(new String[0]);

        PerimeterAreaQuestionData data = new PerimeterAreaQuestionData(question, answer, options, PerimeterAreaQuestionType.TILE_COVERING);
        data.setImageData(imageCode);
        return data;
    }

    private static PerimeterAreaQuestionData generateGridAreaComparisonQuestion() {
        int cols = 12;
        int rows = 6;

        int wA, hA, areaA, wB, hB, areaB, wC, hC, areaC;

        // Ensure all areas are distinct to avoid multiple correct answers
        while (true) {
            wA = 2 + RANDOM.nextInt(3); // 2-4
            hA = 2 + RANDOM.nextInt(4); // 2-5
            areaA = wA * hA;

            wB = 2 + RANDOM.nextInt(3);
            hB = 2 + RANDOM.nextInt(4);
            areaB = wB * hB;

            wC = 2 + RANDOM.nextInt(3);
            hC = 2 + RANDOM.nextInt(4);
            areaC = wC * hC;

            if (areaA != areaB && areaA != areaC && areaB != areaC) {
                break;
            }
        }

        // Colors
        String[] colors = {"#FFEB3B", "#F8BBD0", "#B39DDB", "#81C784", "#4FC3F7"};
        List<String> colorList = new ArrayList<>(Arrays.asList(colors));
        Collections.shuffle(colorList);

        String dataA = "0,1,1," + wA + "," + hA + "," + colorList.get(0) + ",A";
        String dataB = "0,5,1," + wB + "," + hB + "," + colorList.get(1) + ",B";
        String dataC = "0,9,1," + wC + "," + hC + "," + colorList.get(2) + ",C";

        String imageCode = "TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=" + dataA + "|" + dataB + "|" + dataC;

        boolean findMax = RANDOM.nextBoolean();
        String question = findMax ? "Which of the rectangles shown below has the largest area?" : "Which of the rectangles shown below has the smallest area?";
        
        String answer;
        if (findMax) {
            if (areaA >= areaB && areaA >= areaC) answer = "Rectangle A";
            else if (areaB >= areaA && areaB >= areaC) answer = "Rectangle B";
            else answer = "Rectangle C";
        } else {
            if (areaA <= areaB && areaA <= areaC) answer = "Rectangle A";
            else if (areaB <= areaA && areaB <= areaC) answer = "Rectangle B";
            else answer = "Rectangle C";
        }

        String[] options = {"Rectangle A", "Rectangle B", "Rectangle C", "All have equal area"};
        
        PerimeterAreaQuestionData data = new PerimeterAreaQuestionData(question, answer, options, PerimeterAreaQuestionType.GRID_AREA_COMPARISON);
        data.setImageData(imageCode);
        return data;
    }

    private static PerimeterAreaQuestionData generateSameAreaComparisonQuestion() {
        int cols = 14;
        int rows = 9;

        int wC, hC, wD, hD, wE, hE, wF, hF;
        int areaC, areaD, areaE, areaF;

        // Exactly two must have same area
        while (true) {
            wC = 2 + RANDOM.nextInt(3); hC = 3 + RANDOM.nextInt(3); areaC = wC * hC;
            wD = 3 + RANDOM.nextInt(3); hD = 2 + RANDOM.nextInt(3); areaD = wD * hD;
            wE = 4 + RANDOM.nextInt(3); hE = 1 + RANDOM.nextInt(2); areaE = wE * hE;
            wF = 5 + RANDOM.nextInt(2); hF = 1; areaF = wF * hF;

            int[] areas = {areaC, areaD, areaE, areaF};
            Set<Integer> uniqueAreas = new HashSet<>();
            int sameCount = 0;
            for (int a : areas) {
                if (!uniqueAreas.add(a)) sameCount++;
            }

            if (sameCount == 1) { // Exactly one pair matches
                break;
            }
        }

        String color = "#C5E1A5"; // Light green like the image
        // Placed with more horizontal gap and ensuring they fit within boundaries
        // cols=14, rows=9.
        String dataC = "0,2,1," + wC + "," + hC + "," + color + ",(c)";
        String dataD = "0,2,6," + wD + "," + hD + "," + color + ",(d)";
        String dataE = "0,8,1," + wE + "," + hE + "," + color + ",(e)";
        String dataF = "0,8,6," + wF + "," + hF + "," + color + ",(f)";

        String imageCode = "TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=" + dataC + "|" + dataD + "|" + dataE + "|" + dataF;

        String pair = "";
        if (areaC == areaD) pair = "(c) and (d)";
        else if (areaC == areaE) pair = "(c) and (e)";
        else if (areaC == areaF) pair = "(c) and (f)";
        else if (areaD == areaE) pair = "(d) and (e)";
        else if (areaD == areaF) pair = "(d) and (f)";
        else if (areaE == areaF) pair = "(e) and (f)";

        String question = "Which of the 2 shapes shown below have the same area?";
        String answer = pair;
        
        // Generate options
        List<String> options = new ArrayList<>();
        options.add("(c) and (d)");
        options.add("(c) and (e)");
        options.add("(d) and (e)");
        options.add("(e) and (f)");
        options.add("(c) and (f)");
        options.add("(d) and (f)");
        
        List<String> selectedOptions = new ArrayList<>();
        selectedOptions.add(answer);
        Collections.shuffle(options);
        for(String opt : options) {
            if(!selectedOptions.contains(opt)) selectedOptions.add(opt);
            if(selectedOptions.size() == 4) break;
        }
        Collections.shuffle(selectedOptions);

        PerimeterAreaQuestionData data = new PerimeterAreaQuestionData(question, answer, selectedOptions.toArray(new String[0]), PerimeterAreaQuestionType.SAME_AREA_COMPARISON);
        data.setImageData(imageCode);
        return data;
    }

    private static PerimeterAreaQuestionData generateGridMultiShapeQuiz() {
        int cols = 20;
        int rows = 12;

        // Shape definitions (relative to its own top-left 1,1)
        int[][][] templates = {
            {{1,1}, {2,1}, {3,1}, {1,2}, {2,2}}, // 2x3 block minus one
            {{1,1}, {2,1}, {2,2}, {3,2}, {2,3}}, // T-ish shape
            {{1,1}, {1,2}, {1,3}, {1,4}, {2,4}, {3,4}}, // L shape
            {{2,1}, {1,2}, {2,2}, {3,2}, {2,3}}, // Plus shape
            {{1,1}, {2,1}, {3,1}, {4,1}, {5,1}, {6,1}}, // Long strip
            {{1,1}, {2,1}, {1,2}, {1,3}, {2,3}}, // C shape
            {{1,1}, {2,1}, {3,1}, {2,2}, {1,3}, {2,3}, {3,3}}, // I shape
            {{1,1}, {2,1}, {1,2}, {2,2}, {2,3}, {3,3}}  // Z shape
        };

        String[] labels = {"(a)", "(b)", "(c)", "(d)", "(e)"};
        int numShapes = 5;
        
        List<GridShape> shapes = new ArrayList<>();
        boolean[][] occupied = new boolean[cols + 2][rows + 2]; // Extra padding

        for (int i = 0; i < numShapes; i++) {
            int templateIdx = RANDOM.nextInt(templates.length);
            int[][] template = templates[templateIdx];
            
            int attempts = 0;
            while (attempts < 100) {
                int startCol = 1 + RANDOM.nextInt(cols - 6);
                int startRow = 1 + RANDOM.nextInt(rows - 6);
                
                boolean canPlace = true;
                for (int[] cell : template) {
                    int c = startCol + cell[0] - 1;
                    int r = startRow + cell[1] - 1;
                    // Check self and surroundings for 1-cell gap
                    if (c > cols || r > rows || occupied[c][r] || 
                        occupied[c+1][r] || occupied[c-1][r] || 
                        occupied[c][r+1] || occupied[c][r-1]) {
                        canPlace = false;
                        break;
                    }
                }
                
                if (canPlace) {
                    GridShape s = new GridShape();
                    s.label = labels[i];
                    s.cells = new ArrayList<>();
                    for (int[] cell : template) {
                        int c = startCol + cell[0] - 1;
                        int r = startRow + cell[1] - 1;
                        s.cells.add(new int[]{c, r});
                        occupied[c][r] = true;
                    }
                    s.calculateProperties();
                    shapes.add(s);
                    break;
                }
                attempts++;
            }
        }

        int qType = RANDOM.nextInt(7);
        String question = "";
        String answer = "";
        List<String> options = new ArrayList<>();

        switch (qType) {
            case 0: // Area of specific shape
            {
                GridShape s = shapes.get(RANDOM.nextInt(shapes.size()));
                question = "Considering each square is of size 1 cm in width and height, what is the area of shape " + s.label + "?";
                answer = s.area + " sq cm";
                options.add((s.area + 1) + " sq cm");
                options.add((s.area - 1) + " sq cm");
                options.add((s.area + 2) + " sq cm");
                break;
            }
            case 1: // Perimeter of specific shape
            {
                GridShape s = shapes.get(RANDOM.nextInt(shapes.size()));
                question = "Considering each square is of size 1 cm in width and height, what is the perimeter of shape " + s.label + "?";
                answer = s.perimeter + " cm";
                options.add((s.perimeter + 2) + " cm");
                options.add((s.perimeter - 2) + " cm");
                options.add((s.perimeter + 4) + " cm");
                break;
            }
            case 2: // Same area
            {
                GridShape s1 = null, s2 = null;
                for (int i = 0; i < shapes.size(); i++) {
                    for (int j = i + 1; j < shapes.size(); j++) {
                        if (shapes.get(i).area == shapes.get(j).area) {
                            s1 = shapes.get(i); s2 = shapes.get(j); break;
                        }
                    }
                }
                if (s1 != null) {
                    question = "Which 2 shapes have the same area?";
                    answer = s1.label + " and " + s2.label;
                    options.add(shapes.get(0).label + " and " + shapes.get(1).label);
                    options.add(shapes.get(1).label + " and " + shapes.get(2).label);
                    options.add(shapes.get(2).label + " and " + shapes.get(3).label);
                } else {
                    return generateGridMultiShapeQuiz();
                }
                break;
            }
            case 3: // Same perimeter
            {
                GridShape s1 = null, s2 = null;
                for (int i = 0; i < shapes.size(); i++) {
                    for (int j = i + 1; j < shapes.size(); j++) {
                        if (shapes.get(i).perimeter == shapes.get(j).perimeter) {
                            s1 = shapes.get(i); s2 = shapes.get(j); break;
                        }
                    }
                }
                if (s1 != null) {
                    question = "Which 2 shapes have the same perimeter?";
                    answer = s1.label + " and " + s2.label;
                    options.add(shapes.get(0).label + " and " + shapes.get(1).label);
                    options.add(shapes.get(1).label + " and " + shapes.get(2).label);
                    options.add(shapes.get(2).label + " and " + shapes.get(3).label);
                } else {
                    return generateGridMultiShapeQuiz();
                }
                break;
            }
            case 4: // Sum of 2 areas
            {
                int i1 = RANDOM.nextInt(shapes.size());
                int i2 = RANDOM.nextInt(shapes.size());
                while (i1 == i2) i2 = RANDOM.nextInt(shapes.size());
                GridShape s1 = shapes.get(i1);
                GridShape s2 = shapes.get(i2);
                question = "What is the sum of areas of shape " + s1.label + " and " + s2.label + "?";
                int sum = s1.area + s2.area;
                answer = sum + " sq cm";
                options.add((sum + 2) + " sq cm");
                options.add((sum - 1) + " sq cm");
                options.add((sum + 5) + " sq cm");
                break;
            }
            case 5: // Sum of 3 areas
            {
                question = "What is the sum of areas of shape " + shapes.get(0).label + ", " + shapes.get(1).label + " and " + shapes.get(2).label + "?";
                int sum = shapes.get(0).area + shapes.get(1).area + shapes.get(2).area;
                answer = sum + " sq cm";
                options.add((sum + 3) + " sq cm");
                options.add((sum - 2) + " sq cm");
                options.add((sum + 10) + " sq cm");
                break;
            }
            default: // Sum of 2 perimeters
            {
                int i1 = RANDOM.nextInt(shapes.size());
                int i2 = RANDOM.nextInt(shapes.size());
                while (i1 == i2) i2 = RANDOM.nextInt(shapes.size());
                GridShape s1 = shapes.get(i1);
                GridShape s2 = shapes.get(i2);
                question = "What is the sum of perimeters of shape " + s1.label + " and " + s2.label + "?";
                int sum = s1.perimeter + s2.perimeter;
                answer = sum + " cm";
                options.add((sum + 4) + " cm");
                options.add((sum - 2) + " cm");
                options.add((sum + 10) + " cm");
                break;
            }
        }

        StringBuilder imgCode = new StringBuilder("TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=");
        for (int i = 0; i < shapes.size(); i++) {
            GridShape s = shapes.get(i);
            imgCode.append("5,#EC407A,").append(s.label); // Pinkish color like image
            for (int[] cell : s.cells) {
                imgCode.append(",").append(cell[0]).append(",").append(cell[1]);
            }
            if (i < shapes.size() - 1) imgCode.append("|");
        }

        List<String> optList = new ArrayList<>();
        optList.add(answer);
        for (String o : options) {
            if (!optList.contains(o)) optList.add(o);
            if (optList.size() == 4) break;
        }
        while(optList.size() < 4) optList.add(RANDOM.nextInt(50) + " sq cm");
        Collections.shuffle(optList);

        PerimeterAreaQuestionData data = new PerimeterAreaQuestionData(question, answer, optList.toArray(new String[0]), PerimeterAreaQuestionType.GRID_MULTI_SHAPE_QUIZ);
        data.setImageData(imgCode.toString());
        return data;
    }

    private static PerimeterAreaQuestionData generateAreaLogicComparisonQuestion() {
        int cols = 14;
        int rows = 7;

        // Shape (a) - Square/Rectangle
        int wA = 3 + RANDOM.nextInt(2); // 3-4
        int hA = 3 + RANDOM.nextInt(2); // 3-4
        double areaA = wA * hA;

        // Shape (b) - Triangle with base and height
        // Base at top, point at bottom center
        int wB = 4 + RANDOM.nextInt(3); // 4-6
        int hB = 4 + RANDOM.nextInt(3); // 4-6
        double areaB = 0.5 * wB * hB;

        String colorA = "#448AFF"; // Blue
        String colorB = "#FFCC80"; // Light Orange

        String dataA = "0,2,2," + wA + "," + hA + "," + colorA + ",(a)";
        String dataB = "6,8,2," + wB + "," + hB + "," + colorB + ",(b)";

        String imageCode = "TILE-COVERING_COLS=" + cols + "_ROWS=" + rows + "_DATA=" + dataA + "|" + dataB;

        boolean askLess = RANDOM.nextBoolean();
        String question = askLess ? 
            "Is the area of shape (a) less than the area of shape (b) given below?" :
            "Is the area of shape (a) more than the area of shape (b) given below?";
        
        String answer;
        if (askLess) {
            answer = (areaA < areaB) ? "YES" : "NO";
        } else {
            answer = (areaA > areaB) ? "YES" : "NO";
        }

        String[] options = {"YES", "NO", "CANNOT BE DETERMINED", "BOTH HAVE EQUAL AREA"};
        
        PerimeterAreaQuestionData data = new PerimeterAreaQuestionData(question, answer, options, PerimeterAreaQuestionType.AREA_LOGIC_COMPARISON);
        data.setImageData(imageCode);
        return data;
    }

    private static PerimeterAreaQuestionData generateVolumeCubeQuestion() {
        int side = 2 + RANDOM.nextInt(9); // 2-10 cm
        String question = String.format("What is the volume of a cubic box whose sides are %d cm long?", side);
        int volume = side * side * side;
        String answer = volume + " cubic cm";
        
        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add((side * side) + " cubic cm"); // Area distractor
        options.add((4 * side) + " cubic cm"); // Perimeter distractor
        options.add((volume + 10) + " cubic cm");
        
        Collections.shuffle(options);
        return new PerimeterAreaQuestionData(question, answer, options.toArray(new String[0]), PerimeterAreaQuestionType.VOLUME_CUBE);
    }

    private static PerimeterAreaQuestionData generateVolumeCuboidQuestion() {
        int l = 3 + RANDOM.nextInt(7); // 3-9
        int b = 2 + RANDOM.nextInt(l - 1); // 2 to l-1
        int h = 2 + RANDOM.nextInt(5); // 2-6
        
        String question = String.format("Find the volume of a cuboid with length %d cm, breadth %d cm and height %d cm.", l, b, h);
        int volume = l * b * h;
        String answer = volume + " cubic cm";
        
        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add((l + b + h) + " cubic cm");
        options.add((l * b) + " cubic cm");
        options.add((volume - 5) + " cubic cm");
        
        Collections.shuffle(options);
        return new PerimeterAreaQuestionData(question, answer, options.toArray(new String[0]), PerimeterAreaQuestionType.VOLUME_CUBOID);
    }

    private static class GridShape {
        String label;
        List<int[]> cells;
        int area;
        int perimeter;

        void calculateProperties() {
            area = cells.size();
            perimeter = 0;
            for (int[] cell : cells) {
                int c = cell[0];
                int r = cell[1];
                if (!hasCell(c + 1, r)) perimeter++;
                if (!hasCell(c - 1, r)) perimeter++;
                if (!hasCell(c, r + 1)) perimeter++;
                if (!hasCell(c, r - 1)) perimeter++;
            }
        }

        boolean hasCell(int c, int r) {
            for (int[] cell : cells) {
                if (cell[0] == c && cell[1] == r) return true;
            }
            return false;
        }
    }

    private static Question convertToQuestion(PerimeterAreaQuestionData data) {
        Question question = new Question();
        question.setQuestion(data.getQuestion());
        question.setAnswer(data.getAnswer());
        question.setImage(data.getImageData());
        OptionUtils.setQuestionOptions(question, data.getOptions());
        return question;
    }
}
