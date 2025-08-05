package zjg.marketplace.core.valueObjects.cpf;

public class CPF {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "CPF{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
