package dao;

import entidades.Conta;

import java.util.HashMap;
import java.util.Map;

public class ContaDAO implements BaseDAO<Conta> {
    private static Map<Long, Conta> storage = new HashMap<>();
    private static Long idCounter = 1L;

    @Override
    public Conta salvar(Conta conta) {
        conta.setId(idCounter++);
        storage.put(conta.getId(), conta);
        return conta;
    }

    @Override
    public Conta buscarPorId(Long id) {
        return storage.get(id);
    }

    @Override
    public void atualizar(Conta conta) {
        if(conta.getId() != null && storage.containsKey(conta.getId())) {
            storage.put(conta.getId(), conta);
        }
    }

    public Conta buscarPorNumero(String numero) {
        return storage.values().stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }
}