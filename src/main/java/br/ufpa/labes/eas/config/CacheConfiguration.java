package br.ufpa.labes.eas.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.ufpa.labes.eas.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Modalidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Modalidade.class.getName() + ".cursos", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Curso.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Turno.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Turno.class.getName() + ".cursos", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.DemandaCurso.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.DemandaCurso.class.getName() + ".periodoOfertas", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.PeriodoOferta.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Usuario.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Perfil.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Perfil.class.getName() + ".usuarios", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Unidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Unidade.class.getName() + ".cursos", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Unidade.class.getName() + ".demandaCursos", jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Cidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufpa.labes.eas.domain.Cidade.class.getName() + ".unidades", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
