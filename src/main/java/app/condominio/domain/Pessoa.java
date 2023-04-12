import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaDAO {
	private EntityManagerFactory emf;

	// Construtor da classe que inicializa o EntityManagerFactory
	public PessoaDAO() {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
	}

	// Método para adicionar uma pessoa ao banco de dados
	public void adicionarPessoa(Pessoa pessoa) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(pessoa);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Método para atualizar uma pessoa no banco de dados
	public void atualizarPessoa(Pessoa pessoa) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(pessoa);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Método para remover uma pessoa do banco de dados
	public void removerPessoa(Pessoa pessoa) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			pessoa = em.merge(pessoa);
			em.remove(pessoa);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Método para buscar uma pessoa pelo ID no banco de dados
	public Pessoa buscarPessoaPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Pessoa pessoa = null;
		try {
			pessoa = em.find(Pessoa.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return pessoa;
	}

	// Método para buscar todas as pessoas no banco de dados
	public List<Pessoa> buscarTodasPessoas() {
		EntityManager em = emf.createEntityManager();
		List<Pessoa> pessoas = null;
		try {
			pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return pessoas;
	}

	// Método para fechar o EntityManagerFactory
	public void fecharEntityManagerFactory() {
		emf.close();
	}
}
