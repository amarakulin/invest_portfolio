import { OptionsDropdownItem, OptionsDropdownList } from './OptionsDropdownMenuStyles';

const OptionsDropdownMenu = (props) => {
	return (
		<OptionsDropdownList isOpen={props.isOpen}>
			<OptionsDropdownItem>Удалить</OptionsDropdownItem>
			<OptionsDropdownItem>Изменить</OptionsDropdownItem>
		</OptionsDropdownList>
	)
}

export default OptionsDropdownMenu;