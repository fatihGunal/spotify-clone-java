package com.spotifyclone.music.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    // Query JPQL, naming based on java class not db entity
    @Query("select p from Profile p where p.id = ?1")
    Profile findAllById(Integer id);
//    or you can execute native queries:
//    @Query(
//            value = "select * from profile",
//            nativeQuery = true
//    )
}
