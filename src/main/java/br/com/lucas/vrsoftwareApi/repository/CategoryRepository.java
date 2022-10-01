package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, PagingAndSortingRepository<Category,Integer> {
}
