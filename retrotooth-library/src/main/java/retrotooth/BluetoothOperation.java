package retrotooth;


public enum BluetoothOperation {
    // These should match the annotation values
    READ(retrotooth.annotations.READ.class.getSimpleName()),
    WRITE(retrotooth.annotations.WRITE.class.getSimpleName());

    private final String str;

    BluetoothOperation(String str) {
        this.str = str;
    }
}
