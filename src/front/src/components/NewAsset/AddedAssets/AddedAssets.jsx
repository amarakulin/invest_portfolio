import { AddedAssetsTitle, AddedAssetsList, AddedAssetsListItem, AddedAssetsTicker, AddedAssetsCompany, AddedAssetsType } from './AddedAssetsStyles';
import RemoveAsset from './RemoveAsset';

const AddedAssets = ({ data }) => {
	return (
		<>
			<hr></hr>
			<AddedAssetsTitle>Добавленные активы</AddedAssetsTitle>
			<AddedAssetsList>
				<AddedAssetsListItem>
					<AddedAssetsTicker>AXC</AddedAssetsTicker>
					<AddedAssetsCompany>Apple</AddedAssetsCompany>
					<AddedAssetsType>акция</AddedAssetsType>
					<RemoveAsset />
				</AddedAssetsListItem>
			</AddedAssetsList>
		</>
	)
}

export default AddedAssets;