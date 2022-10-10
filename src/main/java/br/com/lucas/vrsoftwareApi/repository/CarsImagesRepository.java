package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Cras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsImagesRepository extends JpaRepository<CarsImages,Integer >, PagingAndSortingRepository<CarsImages,Integer> {

    CarsImages findByCar(Cras cras);


}
