package dao;

// Interface base para todos os DAOs das entidades
public interface BaseDAO<T> {
    T salvar(T entidade);

    T buscarPorId(Long id);

    void atualizar(T entidade);
}