public class Trem extends Thread {

    private Maquinista maquinista;

    public Trem(Maquinista maquinista) {
        this.maquinista = maquinista;
    }

    @Override
    public void run() {
        
        try {
            maquinista.andarTrem(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
