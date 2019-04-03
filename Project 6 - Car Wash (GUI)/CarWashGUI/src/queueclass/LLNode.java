package queueclass;

public class LLNode {
    // Implements String nodes for a Linked Queue
    
    private String info;
    private LLNode link;

    public LLNode(String info) {
        this.info = info;
        this.link = null;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }

    public void setLink(LLNode link) {
        this.link = link;
    }

    public LLNode getLink() {
        return link;
    }
    
}