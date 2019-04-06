package map;

import plan.Step2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MapBuilder {

  private Path file;
  private MapInformation map;
  private MapMatrix mapMatrix;
  private char[][] matrix;

  public MapBuilder(String mapName) {
    this.file = Paths.get(mapName);
    this.map = new MapInformation();
    this.readAndCreateMatrix();
    this.toMapMatrix();
  }

  private void toMapMatrix() {
    Step2D<MapMatrix.Values>[][] mapMatrix = new Step2D[matrix.length][matrix[0].length];

    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[0].length; y++) {
        if (matrix[x][y] == ' ') {
          mapMatrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.ROAD);
        } else {
          mapMatrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.OBSTACLE);
        }
      }
    }

    this.mapMatrix = new MapMatrix(mapMatrix);
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
      String line = null;
      int lineNumber = 0;

      // lines 1 to 4 are metadata default = map
      while ((line = reader.readLine()) != null) {
        lineNumber++;

        switch (lineNumber) {
          case 1:
            this.map.setTitle(line);
            break;
          case 2:
            int[] coordsStart = extractNumbersFromStr(line);

            map.setStartX(coordsStart[0]);
            map.setStartY(coordsStart[1]);

            break;
          case 3:
            int[] coordsEnd = extractNumbersFromStr(line);

            map.setEndX(coordsEnd[0]);
            map.setEndY(coordsEnd[1]);

            break;
          case 4:
            int[] coords = extractNumbersFromStr(line);

            int rows = coords[0];
            int columns = coords[1];

            this.matrix = new char[rows][columns];

            break;
          default:
            for (int i = 0; i < this.matrix[0].length; ++i) {
              this.matrix[lineNumber - 5][i] = line.charAt(i);
            }
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

  public MapInformation getMapInformation() {
    return map;
  }

  public char[][] getMatrix() {
    return matrix;
  }

  public MapMatrix getMapMatrix() {
    return mapMatrix;
  }
}
