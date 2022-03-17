package vt.cn.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}