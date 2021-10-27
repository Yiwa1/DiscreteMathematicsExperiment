package TruthTableMethod;

/**
 *逻辑符号
 * @Auhtor B20070532
 * */

public enum symbol{
    FEI("!"),
    HEQU("&"),
    XIQU("|"),
    DANTIAOJIAN(">"),
    SHUANGTIAOJIAN("-");

    public String sym;

    symbol(String sym) {
        this.sym = sym;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }
}
