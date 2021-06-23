import { OptionsDropdownItem, OptionsDropdownList } from './OptionsDropdownMenuStyles';
import { connect } from 'react-redux'; 
import { deleteAsset } from '../../../../redux/assetsTableReduser';
import { TYPE_BUY, TYPE_SELL } from '../../../../redux/assetsTableReduser';

const dropdownItems = [
	{
		title: 'Удалить',
		onclick: (props) => {
			props.toggleIsOpen(false);
			props.deleteAsset(props.ticker)
		}
	},
	{
		title: 'Купил',
		onclick: (props) => {
			props.toggleIsOpen(false);
			props.setSelectedAsset({ticker: props.ticker, type: TYPE_BUY})
		}
	},
	{
		title: 'Продал',
		onclick: (props) => {
			props.toggleIsOpen(false);
			props.setSelectedAsset({ticker: props.ticker, type: TYPE_SELL})
		}
	}
]

const OptionsDropdownMenu = (props) => {

	return (
		<OptionsDropdownList isOpen={props.isOpen}>
			{
				dropdownItems.map(el => <OptionsDropdownItem 
						key={el.title} 
						onClick={() => el.onclick(props)}
					> 
						{el.title}
					</OptionsDropdownItem>)
			}
		</OptionsDropdownList>
	)
}

export default connect(null, {deleteAsset})(OptionsDropdownMenu);