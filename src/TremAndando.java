public class TremAndando extends Trem{

    public TremAndando(Maquinista maquinista) {
        super(maquinista);
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
