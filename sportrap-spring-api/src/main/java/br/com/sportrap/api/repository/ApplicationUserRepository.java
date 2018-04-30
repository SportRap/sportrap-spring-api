package br.com.sportrap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sportrap.api.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
