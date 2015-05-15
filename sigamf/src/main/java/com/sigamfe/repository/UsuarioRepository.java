package com.sigamfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer>, UsuarioRepositoryCustom {

	public Usuario findByLogin(String login);

	@Query("select a from Usuario a where a.ativo =:ativo")
	public List<Usuario> findByAtivo(@Param("ativo") IndicadorSN ativo);

}
