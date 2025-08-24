package dao;

import entidades.Transacao;

import java.util.HashMap;
import java.util.Map;

public class TransacaoDAO implements BaseDAO<Transacao> {
    private static Map<Long, Transacao> storage = new HashMap<>();
    private static Long idCounter = 1L;

    @Override
    public Transacao salvar(Transacao transacao) {
        transacao.setId(idCounter++);
        storage.put(transacao.getId(), transacao);
        return transacao;
    }

    @Override
    public Transacao buscarPorId(Long id) {
        return storage.get(id);
    }

    @Override
    public void atualizar(Transacao transacao) {
        if(transacao.getId() != null && storage.containsKey(transacao.getId())) {
            storage.put(transacao.getId(), transacao);
        }
    }
}