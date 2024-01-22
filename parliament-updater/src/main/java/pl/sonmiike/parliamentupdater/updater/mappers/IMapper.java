package pl.sonmiike.parliamentupdater.updater.mappers;

public interface IMapper<TDto, TEntity> {

    TEntity mapToEntity(TDto dto);
    TEntity map(TDto dto, TEntity entity);
}
