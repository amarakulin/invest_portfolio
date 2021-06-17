import { AddedAssetsTitle, AddedAssetsList, AddedAssetsListItem, AddedAssetsName } from './AddedAssetsStyles';
import RemoveAsset from './RemoveAsset';

const AddedAssets = ({ data }) => {
	return (
		<>
			<hr></hr>
			<AddedAssetsTitle>Добавленные активы</AddedAssetsTitle>
			<AddedAssetsList>
				{
					data.map((el, i) => {
						return  (
							<AddedAssetsListItem key={i}>
								<strong>{el.ticker}</strong>
								<AddedAssetsName>{el.name}</AddedAssetsName>
								<span>{el.type}</span>
								<span>{el.amount}</span>
								<RemoveAsset id={el.ticker}/>
							</AddedAssetsListItem>
						)
					})
				}
			</AddedAssetsList>
		</>
	)
}

export default AddedAssets;