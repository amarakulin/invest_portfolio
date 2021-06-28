import { OptionsDropdownItem, OptionsDropdownList } from './OptionsDropdownMenuStyles';
import { connect } from 'react-redux'; 
import { deleteAsset } from '../../../../redux/assetsTableReduser';
import { TYPE_BUY, TYPE_SELL } from '../../../../redux/assetsTableReduser';
import { openConfirm, closeConfirm } from '../../../../redux/confirmReduser'

const dropdownItems = [
	{
		title: 'Удалить',
		onclick: (props) => {
			props.openConfirm(props.ticker, () => {
				props.deleteAsset(props.ticker);
				props.showAlert('success', 'Актив успешно изменен');
			});
		}
	},
	{
		title: 'Купить',
		onclick: (props) => {
			props.setSelectedAsset({ticker: props.ticker, type: TYPE_BUY})
		}
	},
	{
		title: 'Продать',
		onclick: (props) => {
			props.setSelectedAsset({ticker: props.ticker, type: TYPE_SELL})
		}
	}
]

const OptionsDropdownMenu = (props) => {
	return (
		<>
			<OptionsDropdownList isOpen={props.isOpen}>
				{
					dropdownItems.map(el => <OptionsDropdownItem 
							key={el.title} 
							onClick={(e) => {
								e.stopPropagation();
								props.toggleIsOpen(false);
								el.onclick(props)
							}}
						> 
							{el.title}
						</OptionsDropdownItem>)
				}
			</OptionsDropdownList>
		</>
	)
}

export default connect(null, {deleteAsset, openConfirm, closeConfirm})(OptionsDropdownMenu);