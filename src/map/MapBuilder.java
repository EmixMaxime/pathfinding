package map;

import plan.Coords2D;
import plan.Step2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MapBuilder {

  private Path file;
  private MapSearch mapSearch;
  private char[][] matrix;

  public MapBuilder(String mapName) {
    this.mapSearch = new MapSearch();
    this.file = Paths.get(mapName);
    this.readAndCreateMatrix();
    this.toMapMatrix();
  }

  private void toMapMatrix() {
    Step2D<MapMatrix.Values>[][] mapMatrix = new Step2D[matrix.length][matrix[0].length];

    for (int y = 0; y < matrix[0].length; y++) {
      for (int x = 0; x < matrix.length; x++) {
        if (matrix[x][y] == ' ') {
          mapMatrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.ROAD);
        } else {
          mapMatrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.OBSTACLE);
        }
      }
    }

    mapSearch.setMap(new MapMatrix(mapMatrix));
  }

  /**
   * get the numbers from a string
   *
   * @param str
   * @return
   */
  private int[] extractNumbersFromStr(String str) {
    return Pattern.compile("[0-9]+")
        .matcher(str)
        .results()
        .map(MatchResult::group)
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  /**
   * Create a Map from a file.
   *
   * <p>First line is the title of the map. 2e 3e 4e info about the size of the map 5e to the end is
   * the map.
   */
  private void readAndCreateMatrix() {
    try (BufferedReader reader = Files.newBufferedReader(file)) {
      String line;
      int lineNumber = 0;
      int yLine = 0;

      // lines 1 to 4 are metadata default = map
      while ((line = reader.readLine()) != null) {
        lineNumber++;

        switch (lineNumber) {
          case 1:
            // @TODO store the title somewhere.
            //            this.map.setTitle(line);
            break;
          case 2:
            int[] coordsStart = extractNumbersFromStr(line);
            Coords2D start = new Coords2D(coordsStart[1], coordsStart[0]);
            mapSearch.setStart(start);
            break;
          case 3:
            int[] coordsEnd = extractNumbersFromStr(line);
            Coords2D goal = new Coords2D(coordsEnd[1], coordsEnd[0]);
            mapSearch.setGoal(goal);
            break;
          case 4:
            int[] coords = extractNumbersFromStr(line);

            int y = coords[0];
            int x = coords[1];

            this.matrix = new char[x][y];

            break;
          default:
            char[] lineArr = line.toCharArray();
            for (int xLine = 0; xLine < lineArr.length; xLine++) {
              this.matrix[xLine][yLine] = lineArr[xLine];
            }

            yLine++;
        }
      }

    } catch (NoSuchFileException e) {
      System.err.format("Map file text '%s' doesn't exist.", e.getMessage());
    } catch (IOException x) {
      System.err.format("IOException: ", x);
    }
  }

  public Path getPath() {
    return file;
  }

  public char[][] getMatrix() {
    return matrix;
  }

  public String matrixToString() {
    StringBuilder str = new StringBuilder();
    for (int y = 0; y < matrix[0].length; y++) {
      for (int x = 0; x < matrix.length; x++) {
        str.append(matrix[x][y]);
      }
      str.append("\n");
    }

    return str.toString();
  }

  public MapSearch getMapSearch() {
    return mapSearch;
  }
}
