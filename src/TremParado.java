public class TremParado extends Trem{

    public TremParado(Maquinista maquinista) {
        super(maquinista);
    }
    
    @Override
    public void run() {
        try {
            maquinista.esperarTrem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
