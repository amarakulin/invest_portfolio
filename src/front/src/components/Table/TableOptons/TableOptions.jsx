import { useRef } from 'react';
import { useDropdown } from '../../../hooks/useDropdown'
import OptionsDropdownMenu from './OptionsDropdownMenu/OptionsDropdownMenu'
import { Dots, DotsWrapper } from './TableOptionsStyles';

const TableOptions = () => {
	const optionsButtonRef = useRef();
	const [isOpen, toggleIsOpen] = useDropdown(optionsButtonRef);

	return (
		<DotsWrapper ref={optionsButtonRef} onClick={toggleIsOpen}>
			<Dots />
			<OptionsDropdownMenu isOpen={isOpen} toggleIsOpen={toggleIsOpen} />
		</DotsWrapper>
	)
}

export default TableOptions;