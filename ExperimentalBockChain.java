import java.security.MessageDigest;
import java.util.ArrayList;

public class ExperimentalBockChain {

    private static ArrayList blockChain;

    public static void main(String[] args) {
        blockChain = new ArrayList();
        blockChain.add(0, getGenesisBlock());
    }

    public static String getHash(int index, String previousHash, long timeStamp, String data) {
        String s = index+previousHash+timeStamp+data;
        return sha256(s);
    }

    public static String getHashFromBlock(Block block) {
        return getHash(block.index, block.prevHash, block.timeStamp, block.data);
    }

    public static Block getNextBlock(String data) {
        Block previousBlock = getLastBlock();
        int nextIndex = ++getLastBlock().index;
        long timeStamp = getCurrentTimeStampInMillisFromToday();
        String hashOfNextBlock = getHash(nextIndex, previousBlock.hash, timeStamp, data);

        return new Block(nextIndex, previousBlock.hash, timeStamp, hashOfNextBlock, data);
    }

    public static Block getGenesisBlock() {
        return new Block(0, "0", 0, "dc7bdce7b3b2e13f0efd8b42d21b61ce354df2c746ce8ba44fedf305b1c3ef19", "genesis block");
    }

    public static boolean isValidBlock(Block block, Block previousBlock) {
        if(previousBlock.index + 1 != block.index) {
            System.out.println("Invalid block: index error");
            return false;
        }
        if(previousBlock.hash != block.prevHash) {
            System.out.println("Invalid block: different hashes");
            return false;
        }
        if(block.hash != getHashFromBlock(block)) {
            System.out.println("Invalid block: incorrect hash");
            return false;
        }
        return true;
    }

    public static void updateBlockChain(Block newBlocks) {

    }

    public static void isValidBlockChain(ArrayList blockChain) {

    }

    public static Block getLastBlock() {
        Block lastBlock = (Block)blockChain.get(blockChain.size()+1);
        return lastBlock;
    }

    public static void addBlock(Block block) {
        if(isValidBlock(block, getLastBlock())) {
            blockChain.add(block);
        }
    }


    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static long getCurrentTimeStampInMillisFromToday() {
        return System.currentTimeMillis() % 1000;
    }
}
