package com.monash.spector.service.repository;


import com.monash.spector.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant,Integer> {
        List<Plant> findBycNameContainingIgnoreCase(String name);

        @Query(value = "select p from Plant p order by p.id")
        Page<Plant> findPlatsWithPage(Pageable pageable);

        @Query(value = "select p from Plant p where p.color like %?1 and p.type like %?2% and p.season like %?3%")
        Page<Plant> findPlantsByFilter(String color, String type, String season, Pageable pageable);

        @Query(value = "SELECT id, TYPE, t_detail, b_name,c_name, color, season, img_link FROM tb_plant ORDER BY selected_num DESC limit 2",
        nativeQuery = true)
        List<Plant> findTopSelected();

        @Modifying(clearAutomatically = true)
        @Query(value = "UPDATE tb_plant SET selected_num = selected_num+1 WHERE id = ?1", nativeQuery = true)
        Integer updateTop(Integer id);


}
