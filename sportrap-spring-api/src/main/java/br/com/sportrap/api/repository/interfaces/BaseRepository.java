package br.com.sportrap.api.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T> extends JpaRepository<T, Long> {

}
