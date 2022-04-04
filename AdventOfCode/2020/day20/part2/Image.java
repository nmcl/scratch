public class Image {
    public Image(int x, int y) {
        _theWorld = new Tile[x][y];
    }

    private Tile[][] _theWorld;
}