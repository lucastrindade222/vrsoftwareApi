package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository< Brand,Integer>, PagingAndSortingRepository<Brand,Integer> {
}
