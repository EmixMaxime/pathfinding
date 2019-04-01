package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MapBuilder {

  private Path file;
  private MapInformation map;
  private int[][] matrix;

  public MapBuilder() {
    this("map");
  }

  public MapBuilder(String mapName) {
    this.map = new MapInformation();
    this.file = Paths.get("resources/" + mapName + ".txt");
    this.readAndCreateMatrix();
  }

  private int[] extractNumbersFromStr(String str) {
    return Pattern.compile("[0-9]+")
        .matcher(str)
        .results()
        .map(MatchResult::group)
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  /**
   * First line is the title of the map. 2e 3e 4e info about the size of the map 5e to the end is
   * the map.
   */
  private void readAndCreateMatrix() {
    try (BufferedReader reader = Files.newBufferedReader(file)) {
      String line = null;
      int lineNumber = 0;

      while ((line = reader.readLine()) != null) {
        lineNumber++;

        switch (lineNumber) {
          case 1:
            this.map.setTitle(line);
            break;
          case 2:
            break;
          case 3:
            break;
          case 4:
            int[] coords = extractNumbersFromStr(line);

            int rows = coords[0];
            int columns = coords[1];

            this.matrix = new int[columns][rows];

            System.out.println(Arrays.toString(coords));
            break;
          default:
             System.out.println(line);

        }
      }

      System.out.println(map.getTitle());

    } catch (NoSuchFileException e) {
      System.err.format("Map file text '%s' doesn't exist.", e.getMessage());
    } catch (IOException x) {
      System.err.format("IOException: ", x);
    }
  }
}
