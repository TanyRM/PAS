package dao;

import java.lang.reflect.Field;
import java.util.*;

public class GenericDAO<T> {
    private final Map<Long, T> storage;
    private Long idCounter = 1L;
    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.storage = new HashMap<>();
    }

    public T salvar(T entidade) {
        try {
            // Usa reflexão para definir o ID
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entidade, idCounter++);

            Long id = (Long) idField.get(entidade);
            storage.put(id, entidade);
            return entidade;
        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar entidade", e);
        }
    }

    public T buscarPorId(Long id) {
        return storage.get(id);
    }

    public void atualizar(T entidade) {
        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            Long id = (Long) idField.get(entidade);

            if(id != null && storage.containsKey(id)) {
                storage.put(id, entidade);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao atualizar entidade", e);
        }
    }

    // Método genérico de busca por campo
    public List<T> buscarPorCampo(String nomeCampo, Object valor) {
        List<T> resultado = new ArrayList<>();

        for(T entidade : storage.values()) {
            try {
                Field campo = entityClass.getDeclaredField(nomeCampo);
                campo.setAccessible(true);
                Object valorCampo = campo.get(entidade);

                if(Objects.equals(valorCampo, valor)) {
                    resultado.add(entidade);
                }
            } catch(Exception e) {
            }
        }

        return resultado;
    }
}