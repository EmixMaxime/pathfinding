package map;

public class MapInformation {
  private String title;
  private int startX;
  private int startY;
  private int endX;
  private int endY;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getStartX(){ return startX;}
  public int getStartY(){ return startY;}

  public void setStartX(int x){ startX = x;}
  public void setStartY(int y){ startY = y;}

  public int getEndX(){ return endX;}
  public int getEndY(){ return endY;}

  public void setEndX(int x){ endX = x;}
  public void setEndY(int y){ endY = y;}
}
