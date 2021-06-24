import { useRef } from 'react';
import { useDropdown } from '../../../hooks/useDropdown'
import OptionsDropdownMenu from './OptionsDropdownMenu/OptionsDropdownMenu'
import { Dots, OptionsDropdownMenuWrapper } from './TableOptionsStyles';

const TableOptions = (props) => {
	const optionsButtonRef = useRef();
	const [isOpen, toggleIsOpen] = useDropdown(optionsButtonRef);

	return (
		<OptionsDropdownMenuWrapper ref={optionsButtonRef} onClick={() => toggleIsOpen(true)}>
			<Dots />
			<OptionsDropdownMenu
				setSelectedAsset={props.setSelectedAsset}
				isOpen={isOpen}
				toggleIsOpen={toggleIsOpen}
				ticker={props.ticker}
			/>
		</OptionsDropdownMenuWrapper>
	)
}

export default TableOptions;