import { OptionsDropdownItem, OptionsDropdownList } from './OptionsDropdownMenuStyles';
import { connect } from 'react-redux'; 
import { deleteAsset } from '../../../../redux/assetsTableReduser';
import { TYPE_BUY, TYPE_SELL } from '../../../../redux/assetsTableReduser';
import useConfirm from '../../../../hooks/useConfirm';
import Confirm from '../../../Confirm/Confirm';

const dropdownItems = [
	{
		title: 'Удалить',
		onclick: (props, open) => {
			open();
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
	const confirm = useConfirm(() => {
		props.deleteAsset(props.ticker);
		props.showAlert('success', 'Актив успешно изменен');
	});

	return (
		<>
			<Confirm {...confirm} />
			<OptionsDropdownList isOpen={props.isOpen}>
				{
					dropdownItems.map(el => <OptionsDropdownItem 
							key={el.title} 
							onClick={(e) => {
								e.stopPropagation();
								props.toggleIsOpen(false);
								el.onclick(props, confirm.open)
							}}
						> 
							{el.title}
						</OptionsDropdownItem>)
				}
			</OptionsDropdownList>
		</>
	)
}

export default connect(null, {deleteAsset})(OptionsDropdownMenu);