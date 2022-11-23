public abstract class Trem extends Thread {

    protected Maquinista maquinista;

    public Trem(Maquinista maquinista) {
        this.maquinista = maquinista;
    }

}
