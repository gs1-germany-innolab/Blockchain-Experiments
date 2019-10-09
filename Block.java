public class Block {

    int index;
    String prevHash;
    long timeStamp;
    String hash;
    String data;

    public Block(int index, String prevHash, long timeStamp, String hash, String data) {
        this.index = index;
        this.prevHash = prevHash;
        this.timeStamp = timeStamp;
        this.hash = hash;
        this.data = data;
    }
}
