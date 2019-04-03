public class LSNode {
    // Implements String and int nodes for a Linked Stack
    
    private String info;
    private int value;
    private LSNode link;

    public LSNode(String info) {
        this.info = info;
        this.link = null;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    
    public LSNode(int value) {
        this.value = value;
        this.link = null;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public void setLink(LSNode link) {
        this.link = link;
    }

    public LSNode getLink() {
        return link;
    }
    
}