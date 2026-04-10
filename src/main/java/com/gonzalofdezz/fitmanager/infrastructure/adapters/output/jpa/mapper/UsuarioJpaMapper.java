package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.UsuarioJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioJpaMapper {
    UsuarioJpaEntity toJpa(Usuario domain);
    Usuario toDomain(UsuarioJpaEntity jpa);
}

