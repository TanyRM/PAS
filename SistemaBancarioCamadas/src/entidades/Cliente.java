package entidades;

public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return String.format("id=%d, nome='%s', cpf='%s', email='%s'",
                id, nome, cpf, email);
    }
}