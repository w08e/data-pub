package com.w08e.data.pub.es;

import com.w08e.data.pub.entity.CityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jinyuewang
 */
@Repository
public interface CityEsRepository extends ElasticsearchRepository<CityEntity, Long> {
}
