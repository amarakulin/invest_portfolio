import { AddedAssetsTitle, AddedAssetsList, AddedAssetsListItem } from './AddedAssetsStyles';
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
								<span>{el.type}</span>
								<span>{el.name}</span>
								<span>{el.amount}</span>
								<RemoveAsset />
							</AddedAssetsListItem>
						)
					})
				}
			</AddedAssetsList>
		</>
	)
}

export default AddedAssets;