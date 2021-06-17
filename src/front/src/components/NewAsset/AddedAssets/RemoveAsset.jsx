import { RemoveAssetButton } from '../../Basic/Button/Button';

const RemoveAsset = () => {
	return (
		<RemoveAssetButton
			onClick={e => {
				e.preventDefault();
			}}
		/>
	)
}

export default RemoveAsset;