import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Cliente {
    private String cpf;
    private String nome;
    private String empreendimento;
    private String unidade;
    private String dataContrato;

    public Cliente(String cpf, String nome, String empreendimento, String unidade, String dataContrato) {
        this.cpf = cpf;
        this.nome = nome;
        this.empreendimento = empreendimento;
        this.unidade = unidade;
        this.dataContrato = dataContrato;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpreendimento() {
        return empreendimento;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getDataContrato() {
        return dataContrato;
    }

    @Override
    public String toString() {
        return String.format("Cliente [CPF: %s, Nome: %s, Empreendimento: %s, Unidade: %s, Data Contrato: %s]",
                cpf, nome, empreendimento, unidade, dataContrato);
    }
}

class Recebimento {
    private String cpf;
    private double valor;
    private String dataRecebimento;
    private String unidade;

    public Recebimento(String cpf, double valor, String dataRecebimento, String unidade) {
        this.cpf = cpf;
        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
        this.unidade = unidade;
    }

    public String getCpf() {
        return cpf;
    }

    public double getValor() {
        return valor;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public String getUnidade() {
        return unidade;
    }

    @Override
    public String toString() {
        return String.format("Recebimento [CPF: %s, Valor: R$%.2f, Data: %s, Unidade: %s]",
                cpf, valor, dataRecebimento, unidade);
    }
}

class SistemaGerenciador {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Recebimento> recebimentos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void registrarRecebimento(Recebimento recebimento) {
        recebimentos.add(recebimento);
        System.out.println("Recebimento registrado com sucesso!");
    }

    public void consultarRecebimentos(String cpf) {
        List<Recebimento> recebimentosCliente = new ArrayList<>();
        for (Recebimento r : recebimentos) {
            if (r.getCpf().equals(cpf)) {
                recebimentosCliente.add(r);
            }
        }

        if (recebimentosCliente.isEmpty()) {
            System.out.println("Nenhum recebimento encontrado para este CPF.");
        } else {
            System.out.println("Recebimentos encontrados:");
            recebimentosCliente.forEach(r -> System.out.println(r));
        }
    }

    public void excluirRecebimento(String cpf, String dataRecebimento) {
        Optional<Recebimento> recebimento = recebimentos.stream()
                .filter(r -> r.getCpf().equals(cpf) && r.getDataRecebimento().equals(dataRecebimento))
                .findFirst();

        if (recebimento.isPresent()) {
            recebimentos.remove(recebimento.get());
            System.out.println("Recebimento excluído com sucesso: " + recebimento.get());
        } else {
            System.out.println("Recebimento não encontrado.");
        }
    }

    public void gerarRelatorio() {
        System.out.println("\n=== Relatório de Recebimentos Excluídos ===");
        if (recebimentos.isEmpty()) {
            System.out.println("Nenhum recebimento registrado.");
        } else {
            recebimentos.forEach(r -> System.out.println(r));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SistemaGerenciador sistema = new SistemaGerenciador();

        Cliente cliente1 = new Cliente("12345678901", "João Silva", "Residencial A", "Apt 101", "01/12/2024");
        sistema.cadastrarCliente(cliente1);

        Recebimento recebimento1 = new Recebimento("12345678901", 1500.0, "02/12/2024", "Apt 101");
        Recebimento recebimento2 = new Recebimento("12345678901", 800.0, "03/12/2024", "Apt 102");
        sistema.registrarRecebimento(recebimento1);
        sistema.registrarRecebimento(recebimento2);

        sistema.consultarRecebimentos("12345678901");
        sistema.excluirRecebimento("12345678901", "02/12/2024");
        sistema.gerarRelatorio();
    }
}
