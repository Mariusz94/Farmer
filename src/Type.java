public enum Type {
    COW("COW"), SHEEP("SHEEP"), PIG("PIG");

    private String text;

    Type(String text) {
        this.text = text;
    }


    public Type ddd() {
        return COW;
    }
}
