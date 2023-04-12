package app.condominio.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Cobranca {

    private static EntityManagerFactory emf;

    // Método para inicializar o EntityManagerFactory
    public static void initEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    // Método para fechar o EntityManagerFactory
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // Método para salvar uma nova cobrança no banco de dados
    public static void salvarCobranca(CobrancaEntity cobranca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cobranca);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para atualizar uma cobrança existente no banco de dados
    public static void atualizarCobranca(CobrancaEntity cobranca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cobranca);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para buscar uma cobrança pelo seu ID
    public static CobrancaEntity buscarCobrancaPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CobrancaEntity.class, id);
        } finally {
            em.close();
        }
    }

    // Método para excluir uma cobrança do banco de dados
    public static void excluirCobranca(CobrancaEntity cobranca) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            cobranca = em.merge(cobranca);
            em.remove(cobranca);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}

// Comentário sobre a classe
/**
 * Classe de utilitário que fornece métodos para realizar operações de persistência (salvar, atualizar, buscar e excluir)
 * em objetos da entidade "CobrancaEntity". Utiliza o Java Persistence API (JPA) para interagir com um banco de dados.
 * Possui métodos para inicializar e fechar o EntityManagerFactory, que é uma fábrica de EntityManager, responsável
 * por criar instâncias de EntityManager, que é a interface principal para interagir com o JPA. Cada método realiza as
 * operações necessárias com o EntityManager e a transação para garantir a consistência dos dados no banco de dados.
 */