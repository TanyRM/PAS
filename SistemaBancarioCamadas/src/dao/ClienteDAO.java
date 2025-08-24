package dao;

import entidades.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ClienteDAO implements BaseDAO<Cliente> {
    private static Map<Long, Cliente> storage = new HashMap<>();
    private static Long idCounter = 1L;

    @Override
    public Cliente salvar(Cliente cliente) {
        cliente.setId(idCounter++);
        storage.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return storage.get(id);
    }

    @Override
    public void atualizar(Cliente cliente) {
        if(cliente.getId() != null && storage.containsKey(cliente.getId())) {
            storage.put(cliente.getId(), cliente);
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        return storage.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}