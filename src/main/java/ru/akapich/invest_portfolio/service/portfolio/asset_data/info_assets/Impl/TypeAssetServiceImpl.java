package ru.akapich.invest_portfolio.service.portfolio.asset_data.info_assets.Impl;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.TypeAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.info_assets.TypeAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info_assets.TypeAssetService;

/**
 * Implementation of {@link TypeAssetService} interface.
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class TypeAssetServiceImpl implements TypeAssetService {

	@Autowired
	private TypeAssetRepository typeAssetRepository;

	@Override
	@Transactional
	public TypeAsset findOrAddExchangeByName(String name) {
		TypeAsset typeAsset;

		typeAsset = typeAssetRepository.findTypeAssetByName(name);
		if (typeAsset == null){
			typeAsset = TypeAsset.builder().name(name).build();
			typeAssetRepository.save(typeAsset);
			log.info(String.format("Add new typeAsset - '%s'", name));
		}
		return typeAsset;
	}
}
