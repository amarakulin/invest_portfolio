import {SmallTitle} from '../../Basic/Title/Title'

const TopAssetsControllerContent = () => {
	return (
		<div>
			<SmallTitle>Мои активы</SmallTitle>
			<div className="FunctionalAssetsControllerContent">
				<button className="CreateCategory"></button>
				<button className="Filter reverse"></button>
			</div>
		</div>
	)
}

export default TopAssetsControllerContent;